## Description
A Java typing game. The user must click start in order to begin. The window displays two words: the word the user must type, and the word the user must type next. The game increases in difficulty the more words the user types, going from difficulty 1 (3-letter words) to difficulty 4 (6-letter words). Each successful word typed is 1 point. The user has 90 seconds to accurately type as many words as possible. The user can reset the game at any time.

Software developed in Eclipse Studio IDE (2060-03). 
Textfiles written in Notepad.
Icons created in Clip Studio Paint (5.0.4). 

This project is currently **complete**. 

## Notes on Development
These notes are primarily for the developer.
* While you can create the JFrame and related code from scratch, it's far more useful to have WindowBuilder installed.
* Open Java file with WindowBuilder in order to change the design of the window.
* My Eclipse version did not come with a WindowBuilder. I needed to use Eclipse Marketplace in order to download it.
* **Potential Improvements**: Add text that tells the player when they type a word incorrectly.
* **Error Handling Blindspots**: Player can still press Start after finishing a game, allowing them to pick up where they left off. It is possible (although unlikely) that the player can "finish" all four difficulties before the timer runs out.
