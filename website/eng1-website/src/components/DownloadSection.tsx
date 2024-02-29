import React from 'react';

const HeroSection = () => {
  return (
    <section id="Content Section" className="xl:p-24 md:p-24 lg:p-24 p-12 pt-20 pb-20 flex justify-center items-center">
      <div className="container text-center">
        <div>
          <h2 className="font-semibold text-3xl md:text-5xl">Our game</h2>
          <p className="pt-5 pb-5 mx-auto max-w-lg text-sm md:text-base">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi commodo tellus sit amet <span className="font-bold">magna tempor </span>vestibulum. Sed lobortis laoreet sem, ut laoreet erat aliquam non. 
          </p>
          <a href="contact">
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
