import React from 'react';

const HeroSection = () => {
  return (
    <section id="Content Section" className="xl:p-24 md:p-24 lg:p-24 p-12 pt-20 pb-20 flex justify-center items-center">
      <div className="container text-center">
        <div>
          <h2 className="font-semibold text-3xl md:text-5xl">Our game</h2>
          <p className="pt-5 pb-5 mx-auto max-w-lg text-sm md:text-base">
            Please note that on macOS the .jar file needs to be started with the -XstartOnFirstThread JVM argument. 
            <br></br>
            <span className="font-semibold">$ java -XstartOnFirstThread -jar desktop-1.0.jar</span>

          </p>
          <a className="cursor-pointer" href="desktop-1.0.jar" target="_blank" rel="noopener noreferrer" download>
            <button className="bg-slate-800 hover:bg-slate-900 text-white p-3 text-sm">
              Download now :)
            </button>
          </a>
        </div>      
      </div>
    </section>
  );
};

export default HeroSection;
