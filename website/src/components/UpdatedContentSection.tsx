import React from 'react';
import { ArrowUpRight } from 'lucide-react';

// Updated HeroSection component
const NewHeroSection = () => {
    return (
        <section id="UpdatedContentSection" className="xl:p-24 md:p-24 lg:p-24 p-5 bg-[#f4f4f4] flex justify-center items-center ">
            <div className="container">
                <h2 className="font-semibold text-5xl pt-5">
                    Updated Deliverables
                </h2>
                <h5 className="text-xl font-semibold text-gray-900 pt-5">By Group 3</h5>
                <h6 className="text-sm font-normal text-gray-500 pb-5">
                    Second sprint of the project. We have made significant improvements to our codebase and documentation. Here are the latest versions of our documents.
                </h6>

                {/* Desktop version */}
                <div className="p-2 hidden md:flex flex-row gap-10 pt-16">
                    <div className="flex flex-col gap-5">
                        <div className="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-6 text-xl font-bold tracking-tight text-gray-900 ">Our Updated GitHub Repository</h5>
                            <p className="font-normal text-gray-500 pb-5">Here is a link to our latest GitHub repository. We've made significant improvements to our codebase.</p>
                            <div className="cursor-pointer ">
                                <div className="text-sm ghosted text-blue-500 flex flex-row gap-1">
                                    <a href="https://github.com/TheRealEmissions/uoy-eng1-2" target="_blank" rel="noopener noreferrer"><h6 >/githubrepository</h6></a>
                                    <ArrowUpRight className="w-5 h-5"/>
                                </div>
                            </div>
                        </div>
                        <div className="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-2 text-xl font-bold tracking-tight text-gray-900 ">Updated Requirements Document</h5>
                            <p className="font-normal text-gray-700 pb-5">Here is the latest version of our requirements document.</p>
                            <a className="cursor-pointer" href="updated_requirements.pdf" target="_blank" rel="noopener noreferrer" download>
                                <h6 className="text-sm ghosted text-blue-500">/updated_requirements.pdf</h6>
                            </a>
                        </div>
                    </div>
                    <div className="flex flex-col gap-5">

                        <div className="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-2 text-xl font-bold tracking-tight text-gray-900 ">Improved Architecture Overview</h5>
                            <p className="font-normal text-gray-700 pb-5">Check out the updated version of our architecture overview.</p>
                            <a className="cursor-pointer" href="updated_architecture.pdf" target="_blank" rel="noopener noreferrer" download>
                                <h6 className="text-sm ghosted text-blue-500">/updated_architecture.pdf</h6>
                            </a>
                        </div>
                        <div className="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-2 text-xl font-bold tracking-tight text-gray-900 ">Revised Method Selection and Planning</h5>
                            <p className="font-normal text-gray-700 pb-5">Here is the latest version of our method selection and planning document.</p>
                            <a className="cursor-pointer" href="revised_methodselection.pdf" target="_blank" rel="noopener noreferrer" download>
                                <h6 className="text-sm ghosted text-blue-500">/revised_methodselection.pdf</h6>
                            </a>
                        </div>
                    </div>
                    <div className="flex flex-col gap-5">
                        <div className="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-2 text-xl font-bold tracking-tight text-gray-900 ">Enhanced Risk Assessment and Mitigation</h5>
                            <p className="font-normal text-gray-700 pb-5 ">Explore the enhanced version of our risk assessment and mitigation document.</p>
                            <a className="cursor-pointer" href="enhanced_riskassessment.pdf" target="_blank" rel="noopener noreferrer" download>
                                <h6 className="text-sm ghosted text-blue-500">/enhanced_riskassessment.pdf</h6>
                            </a>
                        </div>
                        <div className="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            {/* <h6 className="text-sm ghosted">pdf</h6> */}
                            <h5 className="mb-2 text-xl font-bold tracking-tight text-gray-900 ">Updated Implementation Details</h5>
                            <p className="font-normal text-gray-700 pb-5">Check out the latest version of our implementation details.</p>
                            <a className="cursor-pointer" href="updated_implementation.pdf" target="_blank" rel="noopener noreferrer" download>
                                <h6 className="text-sm ghosted text-blue-500">/updated_implementation.pdf</h6>
                            </a>
                        </div>
                    </div>
                </div>

                {/* Phone version  */}
                <div className="md:hidden  gap-10 pt-8 pb-10">
                    <div className="flex gap-3 pb-6">
                        <div className="block max-w-sm p-5 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-6 text-md font-bold tracking-tight text-gray-900 ">Updated GitHub Repository</h5>
                            <p className="text-xs font-normal text-gray-500 pb-5">Here is the latest version of our GitHub repository.</p>
                            <div className="text-sm ghosted text-blue-500 flex flex-row gap-1">
                                <a href="https://github.com/th1583/ENG2" target="_blank" rel="noopener noreferrer"><h6 >/githubrepository</h6></a>
                                <ArrowUpRight className="w-5 h-5"/>
                            </div>
                        </div>
                        <div className="block max-w-sm p-5 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-2 text-md font-bold tracking-tight text-gray-900 ">Updated Requirements Document</h5>
                            <p className="text-xs font-normal text-gray-700 pb-5">Here is the latest version of our requirements document.</p>
                            <a className="cursor-pointer" href="updated_requirements.pdf" target="_blank" rel="noopener noreferrer" download>
                                <h6 className="text-sm ghosted text-blue-500">/updated_requirements.pdf</h6>
                            </a>
                        </div>
                    </div>
                    <div className="flex flex-row gap-3 pb-6">
                        <div className="block max-w-sm p-5 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-2 text-md font-bold tracking-tight text-gray-900 ">Improved Architecture Overview</h5>
                            <p className="text-xs font-normal text-gray-700 pb-5">Check out the updated version of our architecture overview.</p>
                            <a className="cursor-pointer" href="updated_architecture.pdf" target="_blank" rel="noopener noreferrer" download>
                                <h6 className="text-sm ghosted text-blue-500">/updated_architecture.pdf</h6>
                            </a>
                        </div>
                        <div className="block max-w-sm p-5 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-2 text-md font-bold tracking-tight text-gray-900 ">Revised Method Selection <br></br> and Planning</h5>
                            <p className="text-xs font-normal text-gray-700 pb-5">Here is the latest version of our method selection and planning document.</p>
                            <a className="cursor-pointer" href="revised_methodselection.pdf" target="_blank" rel="noopener noreferrer" download>
                                <h6 className="text-sm ghosted text-blue-500">/revised_methodselection.pdf</h6>
                            </a>
                        </div>
                    </div>
                    <div className="flex flex-row gap-3">
                        <div className="block max-w-sm p-5 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-2 text-md font-bold tracking-tight text-gray-900 ">Enhanced Risk Assessment <br></br> and Mitigation</h5>
                            <p className="text-xs font-normal text-gray-700 pb-5">Explore the enhanced version of our risk assessment and mitigation document.</p>
                            <a className="cursor-pointer" href="enhanced_riskassessment.pdf" target="_blank" rel="noopener noreferrer" download>
                                <h6 className="text-sm ghosted text-blue-500">/enhanced_riskassessment.pdf</h6>
                            </a>
                        </div>
                        <div className="block max-w-sm p-5 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            {/* <h6 className="text-sm ghosted">pdf</h6> */}
                            <h5 className="mb-2 text-md font-bold tracking-tight text-gray-900 ">Updated Implementation Details</h5>
                            <p className="text-xs font-normal text-gray-700 pb-5">Check out the latest version of our implementation details.</p>
                            <a className="cursor-pointer" href="updated_implementation.pdf" target="_blank" rel="noopener noreferrer" download>
                                <h6 className="text-sm ghosted text-blue-500">/updated_implementation.pdf</h6>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    )
}

export default NewHeroSection;
