# VERSION 0.0.2 (September 21st, 2026)
Quality of Life update along with some other changes to the game's code structure

**QOL Code Changes** <br>
* Added more comments and documentation to help others understand how the code works.
* Deleted and removed all unused classes, variables, methods, and imports.
* In the `powerUpLogic` class of the `PongGame.java` class, the if/else-if statements were replaced with a single switch
case statement to improve efficiency.

**Audio Changes** <br>
* Changed how the code interfaces with audio, changing from using the (assumedly now deprecated) MediaPlayer and related
classes. The audio now works through the AudioSystem and related classes, and all sound interfacing is done in the new
`Sound.java` class. To see more specific changes, check the source code.
* Deleted many audio files:
    - 'bombsAway.mp3'
    - 'explosion.mp3'
    - 'freeze.mp3'
    - 'wannaPlayPingPong.mp3'
* Converted the remaining audio files from MP3 files to WAV files so they can be used by the AudioSystem class.

**Known Bugs**<br>
* The ball will occassionaly get stuck at the bottom of the screen.
* The ball will occassionaly get stuck in the player's paddle until it eventually gets thrown out with more momentum than it
should as the game will register it being stuck in the paddle as multiple hits of the ball and paddle.

# VERSION 0.0.1 (November 8th, 2025) * FIRST VERSION *

Welcome to my version of PONG!! Hope you enjoy!!
Obviously the base gameplay resembles that of the original Pong,
but!
I have my own additions detailed below!!

* Different ball types that spawn in at random after scoring
    - the Freeze ball, which moves slowly and slows down your paddle upon impact until the next score
    - the Fire ball, which moves quickly and speeds up your paddle upon impact until the next score
    - the Teleportation ball, which teleports your Paddle to a random location upon impact. 
    - the Nuclear Bomb ball, you gotta find out what that does
* The ball speeds up as the current round goes on!
* Cool* sound effects (i think they're cool)
* I think that's the bulk of my additions as of right now?
* More to come!!