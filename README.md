This is the read me containing the development guide for the project.

Map creation:
The map assets are within the assets folder. Download Tiled at https://www.mapeditor.org/ .

Create a new orthogonal map, csv format with a tile size of 16px by 16px and a map size of 120 tiles by 68 tiles resulting in 1920 x 1088 pixels.

To use the assets add a new tileset and make sure you embed them into the map. Once the map has been created, export the map as a png and add all tilesets used into the same folder within the game assets folder. Open the map folder within a text editor and change the image source of each tileset to location within the asset folder. 

Collision Detection and Transitions:
Create a new layer where the collisions will occur. Edit a tileset and select a pixel that you do not use and add a property called blocked and place this pixel where you want collisions to occur and where the play can't go.
For transitions edit a tileset and select a different pixel that you don't use and add a property called transition and give it a value of the file path of where you want the transition to take you.