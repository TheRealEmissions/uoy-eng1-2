import React from 'react'

const HeroSection = () => {
  return (
    <section id="Hero Section" className="pb-32">
        <div className = "container md:mt-20 mt-12 xl:p-24 md:p-24 lg:p-24 p-10 flex gap-20 flex ">
            <div className="md:pt-10 pt-1">
                <div className="max-w-xl md:max-w-3xl">
                    <p className="text-xl md:text-2xl">hello!</p>
                    <h2 className="font-semibold text-4xl  md:text-5xl ">We're <span className=" font-bold">/cohort1/group1</span></h2>
                    <p className = "text-sm md:text-base mt-4 mb-6">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi commodo tellus sit amet magna tempor vestibulum. Sed 
                        <span className=" font-bold"> lobortis laoreet sem,</span> ut laoreet erat aliquam non. 
                    </p>
                    <a href="contact">
                        <button className="bg-slate-800  hover:bg-slate-900 text-white p-3 text-sm">
                            see our work!
                        </button>
                    </a>
                </div>
            </div>
            {/* <div className="h-[38rem] mt-12 lg:mt-0 w-full mx-auto max-w-md overflow-hidden roundedoutline outline-4 outline-offset-4 outline-[#475F45]/40">
                <img className="object-cover w-full h-full rounded" src="" alt="main page" />
            </div> */}
        </div>
    </section>
)
}
export default HeroSection