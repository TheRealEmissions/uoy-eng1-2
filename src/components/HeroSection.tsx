import React from 'react';

const HeroSection = () => {
  return (
    <section id="Hero Section" className="md:pb-32 pb-10">
      <div className="container md:mt-20 mt-12 xl:p-24 md:p-24 lg:p-24 p-10 flex flex-col md:flex-row md:gap-20">
        <div className="md:pt-10 pt-1 ">
          <div className="max-w-xl md:max-w-3xl">
            <p className="text-xl md:text-2xl">hello!</p>
            <h2 className="font-semibold text-4xl md:text-5xl ">We&apos;re <span className=" font-bold">/cohort1/group1</span></h2>
            <p className="text-xs md:text-sm mt-4">All credits for,</p>
            <p className="text-sm md:text-base  mb-6">
              Charlie Piper, Chris Oulton, Dillon Anthony, Ella Daramola, Kevin Thomas, Shirin Sitara Alok Kumar, Tom Haslam. <br></br>
            </p>
            <a href="documents">
              <button className="bg-slate-800  hover:bg-slate-900 text-white p-3 text-sm">
                see our work!
              </button>
            </a>
          </div>
        </div>
        <div className="mt-12 lg:mt-0 w-full mx-auto max-w-md ">
          <img className="rounded-xl w-full" src="map1.png" alt="main page" />
        </div>
      </div>
    </section>
  );
};

export default HeroSection;
