import socket
import threading

HEADER = 64
PORT = 9009
SERVER = socket.gethostbyname(socket.gethostname())
ADDR = (SERVER, PORT)
DISCONNECT_MESSAGE = "!EXIT"
FORMAT = "utf-8"
# Create socket and connect to server
client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

try:
    client.connect(ADDR)
except:
    print("Connection refused")
    exit(1)
def receive():
    while True:
        try:
            message = client.recv(1024).decode(FORMAT)
            if message == DISCONNECT_MESSAGE:
                break

            print("\n" + message)
            print(f"[{ADDR}] ", end="", flush=True)
        except ConnectionResetError:
            print("Server disconnected.")
            break
        except:
            print("Error receiving message.")
            break
    client.close()
def send():
    while True:
        try:
            message = input(f"[{ADDR}] ")
            msg = message.encode(FORMAT)
            msg_length = len(msg)
            send_length = str(msg_length).encode(FORMAT)
            send_length += b' ' * (HEADER - len(send_length))
            client.send(send_length)
            client.send(msg)
            if message == DISCONNECT_MESSAGE:
                break
        except KeyboardInterrupt:
            print("Client terminated by user.")
            exit(0)
        except UnicodeDecodeError:
            break
        except:
            print(f"Error sending message.")
            break

receive_thread = threading.Thread(target=receive)
receive_thread.start()

send_thread = threading.Thread(target=send)
send_thread.start()
