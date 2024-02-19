import React from 'react'
import { ArrowUpRight } from 'lucide-react';

const HeroSection = () => {
  return (
    <section id="Content Section" className="xl:p-24 md:p-24 lg:p-24 p-5 bg-[#f4f4f4] flex justify-center items-center ">
        <div className = "container ">
            <h2 className="font-semibold text-5xl ">
                Our work
            </h2>

            <div className="p-5 flex flex-row gap-10 pt-16">
                <div className="flex flex-col gap-5">
                    <div className="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                    <h5 className="mb-6 text-xl font-bold tracking-tight text-gray-900 ">Our GitHub repository</h5>
                    <p className="font-normal text-gray-500 pb-5">Here is a link to our associated GitHub repository :) we hope you like our code.</p>
                    <a className="cursor-pointer ">
                        <div className="text-sm ghosted text-blue-500 flex flex-row gap-1">
                            <h6 >/githubrepository</h6> 
                            <ArrowUpRight className="w-5 h-5"/>
                        </div>
                    </a>
                    </div>
                    <div className="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                    <h5 className="mb-2 text-xl font-bold tracking-tight text-gray-900 ">Requirements</h5>
                    <p className="font-normal text-gray-700 pb-5">Here is a link to our requirements pdf</p>
                    <a className="cursor-pointer">
                        <h6 className="text-sm ghosted text-blue-500">/requirements.pdf</h6>
                    </a>
                    </div>
                </div>
                <div className="flex flex-col gap-5">

                    <div className="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                    <h5 className="mb-2 text-xl font-bold tracking-tight text-gray-900 ">Architecture</h5>
                    <p className="font-normal text-gray-700 pb-5">Here is a link to our architecture pdf</p>
                    <a className="cursor-pointer">
                        <h6 className="text-sm ghosted text-blue-500">/architecture.pdf</h6>
                    </a>
                    </div>
                    <div className="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                    <h5 className="mb-2 text-xl font-bold tracking-tight text-gray-900 ">Method selection and planning</h5>
                    <p className="font-normal text-gray-700 pb-5">Here is a link to our method selection and planning pdf</p>
                    <a className="cursor-pointer">
                        <h6 className="text-sm ghosted text-blue-500">/methodselectionandplanning.pdf</h6>
                    </a>
                    </div>
                </div>
                <div className="flex flex-col gap-5">
                    <div className="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                    <h5 className="mb-2 text-xl font-bold tracking-tight text-gray-900 ">Risk assessment and mitigation </h5>
                    <p className="font-normal text-gray-700 ">Here is a link to our Risk assessment and mitigation and planning pdf</p>
                    <a className="cursor-pointer ">
                        <h6 className="text-sm ghosted text-blue-500">/riskassessment.pdf</h6>
                    </a>
                    </div>
                    <div className="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                    {/* <h6 className="text-sm ghosted">pdf</h6> */}
                    <h5 className="mb-2 text-xl font-bold tracking-tight text-gray-900 ">Implementation</h5>
                    <p className="font-normal text-gray-700 pb-5">Here is a link to our implementation pdf</p>
                    <a className="cursor-pointer">
                        <h6 className="text-sm ghosted text-blue-500">/implementation.pdf</h6>
                    </a>
                    </div>
                </div>

                
            </div>
            {/* <div className="pt-20 justify-center text-center">
                <div>
                    
                </div>
                <h2 className="font-semibold text-5xl">
                    Our game
                </h2>
                <p className="pt-5 pb-5 mx-auto max-w-lg">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi commodo tellus sit amet magna tempor vestibulum. Sed lobortis laoreet sem, ut laoreet erat aliquam non. 
                </p>
                <a href="contact">
                    <button className="bg-slate-800  hover:bg-slate-900 text-white p-3 text-sm">
                        Download now :)
                    </button>
                </a>
            </div> */}

        </div>



        


    </section>
  )
}

export default HeroSection