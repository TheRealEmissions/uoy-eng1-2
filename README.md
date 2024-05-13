This is the read me containing the development guide for the project.

Map creation:
The map assets are within the assets folder. Download Tiled at https://www.mapeditor.org/ .

Create a new orthogonal map, csv format with a tile size of 16px by 16px and a map size of 120 tiles by 68 tiles resulting in 1920 x 1088 pixels.

To use the assets add a new tileset and make sure you embed them into the map. Once the map has been created, export the map as a png and add all tilesets used into the same folder within the game assets folder. Open the map folder within a text editor and change the image source of each tileset to location within the asset folder. 

Collision Detection and Transitions:
Create a new layer where the collisions will occur. Edit a tileset and select a pixel that you do not use and add a property called blocked and place this pixel where you want collisions to occur and where the playScreen can't go.
For transitions edit a tileset and select a different pixel that you don't use and add a property called transition and give it a value of the file path of where you want the transition to take you.


# Tests

The testing part of this code is derived from [TomGrill's libGDX Testing Skeleton](https://github.com/TomGrill/gdx-testing).

# Website

The **[website](https://charliepiper.github.io)** (for ENG1 cohort1/group1)
 

## Project Structure 
The project structure is organised as follows:

```
ENG1/
├── src/app/			# Contains Next.js pages
│   ├── page.tsx			# Home page
│   ├── layout.tsx			# Home page layout
│
├── src/components/		# Reusable components
│   ├── ContentSection.tsx		# Links to documents component
│   ├── DownloadSection.tsx		# Game download component
│   ├── Footer.js			# Footer component
│   ├── HeroSection.tsx			# Hero component
│   ├── Navbar.tsx			# Navbar component (both phone and desktop)
│
├── public/				# Static assets
│   └── ...				# Other assets
│
├── styles/				# Global styles
│   └── globals.css			# Global CSS file
│
├── README.md				# Project documentation
├── package.json			# Node.js dependencies and scripts
└── ...
```

## Technologies / Frameworks

-- **[Next.js](https://nextjs.org)** 

-- **[TailwindCSS](https://tailwindcss.com)**


## Setup

To set up the project locally, follow these steps:

1. Clone the repository:
   ```bash
   git clone <repository-url> (for this fork (will change) https://github.com/TheRealEmissions/uoy-eng1-2.git)
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm run dev
   ```

4. Open [http://localhost:3000](http://localhost:3000) in your browser to view the website.


