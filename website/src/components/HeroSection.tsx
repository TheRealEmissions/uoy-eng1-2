import React from 'react';

const HeroSection = () => {
  return (
    <section id="Hero Section" className="md:pb-32 pb-10 flex justify-center items-center ">
      <div className="container md:flex-row flex-col flex md:mt-20 mt-12 xl:p-24 md:p-24 lg:p-24 p-10 md:gap-20 ">
        <div className="md:pt-10 pt-1 max-w-xl ">
          <div className="max-w-xl md:max-w-3xl">
            <p className="text-xl md:text-2xl">hello!</p>
              <h2 className="font-semibold text-4xl md:text-5xl ">
                We&apos;re cohort1/<span style={{textDecoration: 'line-through'}}>group1</span>
                <span className="font-bold"> group3</span>
              </h2>
            <p className="text-xs md:text-sm mt-4"><br></br> Updated credits for,</p>
            <p className="text-sm md:text-base  mb-6">
              Lucy Crabtree, Liam Martin, Aaliya Williams, Kai Nichol, Sammy Hori, Tim Gorst, Zac Ribbins.
              <br></br>
            </p>
            <p className="text-xs md:text-sm mt-4">Original credits for,</p>
            <p className="text-sm md:text-base  mb-6">
              Charlie Piper, Chris Oulton, Dillon Anthony, Ella Daramola, Kevin Thomas, Shirin Sitara Alok Kumar, Tom
              Haslam. <br></br>
            </p>
            <a href="/updated_documents">
              <button className="bg-slate-800  hover:bg-slate-900 text-white p-3 text-sm">
                Updated Diagrams
              </button>
            </a>
          </div>
        </div>
        <div className="pt-12 lg:mt-0 w-full mx-auto max-w-md ">
          <img className="rounded-xl w-full" src="/old_deliverables/map1.png" alt="main page"/>
        </div>

      </div>
    </section>
  );
};

export default HeroSection;
