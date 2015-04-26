# NAO_remote_control
Control NAO thanks to a remote

Server :
Wait for an order given by a socket in the format 123#say#Hello World!$

For the move to, parameters are :
- direction X (m)
- direction Y (m)
- theta angle (° beetween [-180, 180])
Message : "123#walk#0.0#0.0#-10$"