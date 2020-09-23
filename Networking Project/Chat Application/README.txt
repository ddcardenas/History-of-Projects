1) Member’s contributions:

Member: Daniel Cardenas
Worked on connect function, send function, server initalization and use.  
Also worked on duplication, however, Daniel's code was eventually chosen to be used.
Established fd_set structure readfds for use with checking FD_ISSET for STDIN, the listening socket (master_socket), 
and all existing connection sockets in our list.
Created buffers for tokenization of STDIN (along with implementing read() function) and sending and receiving messages.
Implementing and testing the select() function for activity.

Member: Haemin
During the beginning of our project, we started with C++ and Windows. 
Tried to code connect function in it but didn't successed.
In C++, we finished the myIP menu option, however, issues attempting to finalize menu options in C++ were unsuccessful, thus we moved our source code to C in Linux, by using CYGWIN in our Windows OS laptops.
Worked in myIP, Terminate and List, checking duplicated, help, tried to find error together at send and recieve.
Worked connect part, however Daniel finished connect worked first, we use daniels connect. 

Mutual Contributions:
Expanded knowledge of C and C++ during development of code.  
Researched pertinent functions and coding methods to ensure successful integration of programming techniques.
Verification and Validation testing.  
Ensured project requirements were met.  
Performed error detection and implemented testing of corrections.


2) Installation Instructions (prerequisites, how to build and run application):
	1. Requires linux operating system.
	2. gcc compiler
	3. If using a virtual machine in windows, the 'myip' menu option may provide an incorrect ip address.  
		In this case, please consult 'ipconfig' in your main windows command prompt.
	4. In command prompt type in 'gcc chat.c -o chat' to compile source code 
	5. After it compiles, type in './chat <portnumber>' (any number you want) to execute