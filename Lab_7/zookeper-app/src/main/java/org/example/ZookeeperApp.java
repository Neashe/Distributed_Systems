package org.example;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZookeeperApp extends JFrame {
    private static final String ZNODE_PATH = "/a";
    private ZooKeeper zk;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode rootNode;
    private JLabel childrenCountLabel;
    private CountDownLatch latch = new CountDownLatch(1);

    private class ZooKeeperWatcher implements Watcher {
        @Override
        public void process(WatchedEvent event) {
            try {
                if (event.getType() == Event.EventType.NodeCreated && ZNODE_PATH.equals(event.getPath())) {
                    System.out.println("Node " + ZNODE_PATH + " has been created.");
                    updateUIForNodeCreated();
                    latch.countDown();
                } else if (event.getType() == Event.EventType.NodeDeleted && ZNODE_PATH.equals(event.getPath())) {
                    System.out.println("Node " + ZNODE_PATH + " has been deleted.");
                    updateUIForNodeDeleted();
                } else if (event.getType() == Event.EventType.NodeChildrenChanged && event.getPath().startsWith(ZNODE_PATH)) {
                    System.out.println("Children of " + ZNODE_PATH + " have changed.");
                    updateChildren(ZNODE_PATH);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ZookeeperApp() {
        setTitle("ZooKeeper App");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        rootNode = new DefaultMutableTreeNode(ZNODE_PATH);
        treeModel = new DefaultTreeModel(rootNode);
        JTree tree = new JTree(treeModel);
        JScrollPane treeScrollPane = new JScrollPane(tree);

        childrenCountLabel = new JLabel("Current number of descendants: 0");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(treeScrollPane, BorderLayout.CENTER);
        panel.add(childrenCountLabel, BorderLayout.SOUTH);

        add(panel);
    }

    public void startZookeeper() throws IOException, InterruptedException, KeeperException {
        zk = new ZooKeeper("127.0.0.1:2181", 3000, new ZooKeeperWatcher());
        zk.addWatch(ZNODE_PATH, AddWatchMode.PERSISTENT_RECURSIVE);

        Stat stat = zk.exists(ZNODE_PATH, true);
        if (stat != null) {
            updateUIForNodeCreated();
            latch.countDown();
        } else {
            System.out.println("Node " + ZNODE_PATH + " does not exist. Waiting for creation...");
        }

        latch.await();
    }

    private void updateUIForNodeCreated() throws KeeperException, InterruptedException {
        SwingUtilities.invokeLater(() -> setVisible(true));
        updateChildren(ZNODE_PATH);
    }

    private void updateUIForNodeDeleted() throws InterruptedException, KeeperException {
        SwingUtilities.invokeLater(() -> setVisible(false));
        zk.exists(ZNODE_PATH, true);
    }

    private void updateChildren(String path) throws KeeperException, InterruptedException {
        List<String> children = zk.getChildren(path, true);
        int descendantsCount = countDescendants(ZNODE_PATH);

        SwingUtilities.invokeLater(() -> {
            childrenCountLabel.setText("Current number of descendants: " + descendantsCount);
            try {
                refreshTree();
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private int countDescendants(String path) throws KeeperException, InterruptedException {
        List<String> children = zk.getChildren(path, true);
        int count = children.size();
        for (String child : children) {
            count += countDescendants(path + "/" + child);
        }
        return count;
    }

    private void refreshTree() throws KeeperException, InterruptedException {
        rootNode.removeAllChildren();
        buildTree(rootNode, ZNODE_PATH);
        treeModel.reload();
    }

    private void buildTree(DefaultMutableTreeNode node, String path) throws KeeperException, InterruptedException {
        List<String> children = zk.getChildren(path, true);
        for (String child : children) {
            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
            node.add(childNode);
            buildTree(childNode, path + "/" + child);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ZookeeperApp app = new ZookeeperApp();
            try {
                app.startZookeeper();
            } catch (IOException | InterruptedException | KeeperException e) {
                e.printStackTrace();
            }
        });
    }
}



