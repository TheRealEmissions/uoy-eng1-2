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
   git clone <repository-url> (for now (will change) https://github.com/charliepiper/charliepiper.github.io.git)
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