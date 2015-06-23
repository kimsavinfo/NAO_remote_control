# Robot NAO : Remote Control

Control NAO thanks to a remote

To see the demo, please watch the video "/projet/NAO_remote_demo_1080p.mp4"

### How does it work ?
![alt tag](/projet/demp_01.png)

### The server : Choregraphe IDE
![alt tag](/projet/demp_02.png)

Waiting for an order given by a socket in the format 123#say#Hello World!$

For the move to, parameters are :
- direction X (m)
- direction Y (m)
- theta angle (° beetween [-180, 180])
Message : "123#walk#0.0#0.0#-10$"

### The Android interface
![alt tag](/projet/demp_03.png)