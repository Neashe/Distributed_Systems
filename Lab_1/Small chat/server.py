import socket
import threading

HEADER = 64
PORT = 9009
SERVER = socket.gethostbyname(socket.gethostname())
ADDR = (SERVER, PORT)
DISCONNECT_MESSAGE = "!EXIT"
FORMAT = "utf-8"

serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serverSocket.bind(ADDR)

clients = []

print("PYTHON TCP SERVER STARTED")
def broadcast(message, conn):
    for client in clients:
        if client != conn:
            client.send(message)
def handle_client(conn, addr):
    print(f"[NEW CONNECTION] {addr} connected")
    connected = True
    while connected:
        try:
            msg_length = conn.recv(HEADER).decode(FORMAT)
            if msg_length:
                msg_length = int(msg_length)
                msg = conn.recv(msg_length).decode(FORMAT)

                if msg == DISCONNECT_MESSAGE:
                    # clients.remove(conn)
                    print(f"[DISCONNECTED] {addr}")
                    connected = False
                    conn.send(DISCONNECT_MESSAGE.encode(FORMAT))
                else:
                    print(f"[{addr}] {msg}")
                    broadcast(f"[{addr}] {msg}".encode(FORMAT), conn)
        except ConnectionResetError:
            print(f"[DISCONNECT] {addr} disconnected unexpectedly")
            clients.remove(conn)
            break
        except:
            print(f"Error handling connection with {addr}")
            break
    conn.close()
    if conn in clients:
        clients.remove(conn)

def start():
    serverSocket.listen()
    print(f"Server is listening on {SERVER}")
    while True:
        conn, addr = serverSocket.accept()
        clients.append(conn)
        thread = threading.Thread(target=handle_client, args=(conn, addr))
        thread.start()
        print(f"[ACTIVE CONNECTIONS] {threading.activeCount() - 1}")

start()
