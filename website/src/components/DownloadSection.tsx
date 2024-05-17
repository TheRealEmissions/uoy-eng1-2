import React from 'react';

const HeroSection = () => {
  return (
    <section id="Content Section" className="xl:p-24 md:p-24 lg:p-24 p-12 pt-20 pb-20 flex justify-center items-center">
      <div className="container text-center">
        <div>
          <h2 className="font-semibold text-3xl md:text-5xl">Heslington Hustle 2.0</h2>
          <p className="pt-5 pb-5 mx-auto max-w-lg text-sm md:text-base">
            Please note that on macOS the .jar file needs to be started with the -XstartOnFirstThread JVM argument. 
            <br></br>
            <span className="font-semibold">$ java -XstartOnFirstThread -jar heslington_hustle.jar</span>

          </p>
          <a className="cursor-pointer" href="/updated_deliverables/heslington_hustle.jar" target="_blank" rel="noopener noreferrer" download>
            <button className="bg-slate-800 hover:bg-slate-900 text-white p-3 text-sm">
              Download Here :)
            </button>
          </a>
        </div>      
      </div>
    </section>
  );
};

export default HeroSection;
