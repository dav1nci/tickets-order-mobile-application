# Tickets Order

I have developed a mobile service for buying tickets online . “Online” in my application meant that my phone using a Socket connected to my laptop via Wi-Fi and sends him JSON which sealed object that keeps the order data, then laptop save it in Postgresql database, and then the phone save same information in a local SQLite database .Server part I  also wrote by myself, server open ServerSocket that waits for connections on the specified port , and when the connection is made it make saving order.
##### In this application I have used: 
+ Android SDK
+ multithreading
+ Web Sockets
+ Postgresql database
+ SQLite database
+ Spring Framework(in server side)
+ JSON
