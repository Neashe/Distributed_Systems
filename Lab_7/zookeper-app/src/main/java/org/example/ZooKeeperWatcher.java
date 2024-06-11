package org.example;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.CountDownLatch;

public class ZooKeeperWatcher implements Watcher {
    private static ZooKeeper zooKeeper;
    private static CountDownLatch connectedSignal = new CountDownLatch(1);
    private static SwingApp swingApp;
    private static final int SESSION_TIMEOUT_MS = 3000;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = new ZooKeeper("localhost:2181", SESSION_TIMEOUT_MS, new ZooKeeperWatcher());
        connectedSignal.await();

        // Dodanie watchera dla node'a /a
        Stat stat = zooKeeper.exists("/a", true);
        if (stat != null) {
            zooKeeper.addWatch("/a", AddWatchMode.PERSISTENT_RECURSIVE);
            printChildrenCount("/a"); // Update count on start if node already exists
        }

        enterInputLoop();
    }

    private static void enterInputLoop() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String input = br.readLine();
                if (input.equals("quit")) {
                    zooKeeper.close();
                    stopSwingApp();
                    break;
                } else if (input.equals("tree")) {
                    printTree("/a");
                }
            } catch (IOException | InterruptedException | KeeperException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void process(WatchedEvent event) {
        try {
            String path = event.getPath();
            Event.EventType type = event.getType();

            System.out.println("Received event: " + type + " for path: " + path);

            if (type == Event.EventType.None) {
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    System.out.println("Successfully connected to ZooKeeper");
                    connectedSignal.countDown();
                }
            } else if (type == Event.EventType.NodeCreated && path.equals("/a")) {
                System.out.println("Node /a created");
                startSwingApp();
                printChildrenCount(path);
            } else if (type == Event.EventType.NodeDeleted && path.equals("/a")) {
                System.out.println("Node /a deleted");
                stopSwingApp();
            } else if (type == Event.EventType.NodeChildrenChanged) {
                System.out.println("HAHA!");
                printChildrenCount(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void startSwingApp() {
        if (swingApp == null) {
            swingApp = new SwingApp();
            swingApp.show();
            System.out.println("Swing app should be working...");
        }
    }

    private static void stopSwingApp() {
        if (swingApp != null) {
            swingApp.close();
            swingApp = null;
        }
    }

    private static void printChildrenCount(String path) throws KeeperException, InterruptedException, IOException {
        int childrenCount = zooKeeper.getChildren(path, false).size();
        System.out.println("Children count of /a: " + childrenCount);
        updateSwingApp(childrenCount);
    }

    private static void updateSwingApp(int count) {
        if (swingApp != null) {
            swingApp.updateCount(count);
        }
    }

    private static void printTree(String path) throws KeeperException, InterruptedException {
        List<String> tree = getTree(path);
        for (String treePart : tree) {
            StringBuilder sb = new StringBuilder();
            StringTokenizer tokenizer = new StringTokenizer(treePart, "/");
            String token = null;
            while (tokenizer.hasMoreTokens()) {
                if (token != null) {
                    sb.append("    ");
                }
                token = tokenizer.nextToken();
            }
            sb.append("+-- ");
            sb.append(token);
            System.out.println(sb.toString());
        }
    }

    private static List<String> getTree(String zNodeName) throws KeeperException, InterruptedException {
        List<String> tree = new java.util.ArrayList<>();
        java.util.Stack<String> stack = new java.util.Stack<>();
        stack.push(zNodeName);
        while (!stack.empty()) {
            String current = stack.pop();
            try {
                List<String> children = zooKeeper.getChildren(current, false);
                children.sort(java.util.Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
                children.forEach(ch -> stack.push(String.format("%s/%s", current, ch)));
                tree.add(current);
            } catch (KeeperException e) {
                if (e.code().name().equals("NONODE")) {
                    if (e.getPath().equals(zNodeName)) {
                        System.out.println("zNode `" + zNodeName + "` doesn't exist");
                    } else {
                        System.out.println("Expected zNode `" + e.getPath() + "` but was missing");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return tree;
    }
}
