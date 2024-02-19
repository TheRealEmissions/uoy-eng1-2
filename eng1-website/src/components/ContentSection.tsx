import React from 'react'
import { ArrowUpRight } from 'lucide-react';

// Links section showcasing pdfs and github repository
const HeroSection = () => {
  return (
    <section id="Content Section" className="xl:p-24 md:p-24 lg:p-24 p-5 bg-[#f4f4f4] flex justify-center items-center ">
        <div className = "container ">
            <h2 className="font-semibold text-5xl ">
                Our work
            </h2>

            {/* Desktop version */}
            <div className="p-5 hidden md:flex flex-row gap-10 pt-16">
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
            
            {/* Phone version  */}
            <div className="md:hidden p-5 gap-10 pt-16">
                <div className="flex gap-5 pb-6">
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
                <div className="flex flex-row gap-5 pb-6">
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
                <div className="flex flex-row gap-5">
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


        </div>



        


    </section>
  )
}

export default HeroSection