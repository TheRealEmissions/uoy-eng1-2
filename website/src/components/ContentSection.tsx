import React from 'react';
import { ArrowUpRight } from 'lucide-react';

// Updated HeroSection component
const HeroSection = () => {
    return (
        <section id="ContentSection"
                 className="xl:p-24 md:p-24 lg:p-24 p-5 bg-[#f4f4f4] flex justify-center items-center ">
            <div className="container">
                <h2 className="font-semibold text-5xl pt-5">
                    Original Deliverables
                </h2>
                <h5 className="text-xl font-semibold text-gray-900 pt-5">By Group 1</h5>
                {/* Desktop version */}
                <div className="p-2 hidden md:flex flex-row gap-10 pt-16">
                    <div className="flex flex-col gap-5">
                        <div
                            className="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-6 text-xl font-bold tracking-tight text-gray-900 ">Our GitHub
                                repository</h5>
                            <p className="font-normal text-gray-500 pb-5">Here is a link to our associated GitHub
                                repository. We hope you appreciate our code.</p>
                            <div className="cursor-pointer ">
                                <div className="text-sm ghosted text-blue-500 flex flex-row gap-1">
                                    <a href="https://github.com/Atomix77/ENG1" target="_blank">
                                        <h6>/github repository</h6>
                                    </a>
                                    <ArrowUpRight className="w-5 h-5"/>
                                </div>
                            </div>
                        </div>
                        <div
                            className="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-2 text-xl font-bold tracking-tight text-gray-900 ">Requirements</h5>
                            <p className="font-normal text-gray-700 pb-5">Here is a link to our requirements PDF.</p>
                            <a className="cursor-pointer" href="/old_deliverables/requirements.pdf" target="_blank"
                               rel="noopener noreferrer" download>
                                <h6 className="text-sm ghosted text-blue-500">/requirements.pdf</h6>
                            </a>
                        </div>
                    </div>
                    <div className="flex flex-col gap-5">

                        <div
                            className="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-2 text-xl font-bold tracking-tight text-gray-900 ">Architecture</h5>
                            <p className="font-normal text-gray-700 pb-5">Here is a link to our architecture PDF.</p>
                            <a className="cursor-pointer" href="/old_deliverables/architecture.pdf" target="_blank"
                               rel="noopener noreferrer" download>
                                <h6 className="text-sm ghosted text-blue-500">/architecture.pdf</h6>
                            </a>
                        </div>
                        <div
                            className="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-2 text-xl font-bold tracking-tight text-gray-900 ">Method selection and
                                planning</h5>
                            <p className="font-normal text-gray-700 pb-5">Here is a link to our method selection and
                                planning PDF.</p>
                            <a className="cursor-pointer" href="/old_deliverables/methodselection.pdf" target="_blank"
                               rel="noopener noreferrer" download>
                                <h6 className="text-sm ghosted text-blue-500">/methodselectionandplanning.pdf</h6>
                            </a>
                        </div>
                    </div>
                    <div className="flex flex-col gap-5">
                        <div
                            className="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-2 text-xl font-bold tracking-tight text-gray-900 ">Risk assessment and
                                mitigation </h5>
                            <p className="font-normal text-gray-700 pb-5 ">Here is a link to our risk assessment and
                                mitigation PDF.</p>
                            <a className="cursor-pointer" href="/old_deliverables/riskassessment.pdf" target="_blank"
                               rel="noopener noreferrer" download>
                                <h6 className="text-sm ghosted text-blue-500">/riskassessment.pdf</h6>
                            </a>
                        </div>
                        <div
                            className="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-2 text-xl font-bold tracking-tight text-gray-900 ">Implementation</h5>
                            <p className="font-normal text-gray-700 pb-5">Here is a link to our implementation PDF.</p>
                            <a className="cursor-pointer" href="/old_deliverables/implementation.pdf" target="_blank"
                               rel="noopener noreferrer" download>
                                <h6 className="text-sm ghosted text-blue-500">/implementation.pdf</h6>
                            </a>
                        </div>
                    </div>
                </div>
                <br/>
                <a href="/documents">
                    <button className="bg-slate-800  hover:bg-slate-900 text-white p-3 text-sm">
                        Original Diagrams
                    </button>
                </a>

                {/* Phone version  */}
                <div className="md:hidden gap-10 pt-8 pb-10">
                    <div className="flex gap-3 pb-6">
                        <div
                            className="block max-w-sm p-5 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-6 text-md font-bold tracking-tight text-gray-900 ">Our GitHub
                                repository</h5>
                            <p className="text-xs font-normal text-gray-500 pb-5">Here is a link to our associated
                                GitHub repository. We hope you appreciate our code.</p>
                            <div className="text-sm ghosted text-blue-500 flex flex-row gap-1">
                                <a href="https://github.com/th1583/ENG1" target="_blank"><h6>/githubrepository</h6></a>
                                <ArrowUpRight className="w-5 h-5"/>
                            </div>
                        </div>
                        <div
                            className="block max-w-sm p-5 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-2 text-md font-bold tracking-tight text-gray-900 ">Requirements</h5>
                            <p className="text-xs font-normal text-gray-700 pb-5">Here is a link to our requirements
                                PDF.</p>
                            <a className="cursor-pointer" href="/old_deliverables/requirements.pdf" target="_blank"
                               rel="noopener noreferrer" download>
                                <h6 className="text-sm ghosted text-blue-500">/requirements.pdf</h6>
                            </a>
                        </div>
                    </div>
                    <div className="flex flex-row gap-3 pb-6">
                        <div
                            className="block max-w-sm p-5 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-2 text-md font-bold tracking-tight text-gray-900 ">Architecture</h5>
                            <p className="text-xs font-normal text-gray-700 pb-5">Here is a link to our architecture
                                PDF.</p>
                            <a className="cursor-pointer" href="/old_deliverables/architecture.pdf" target="_blank"
                               rel="noopener noreferrer" download>
                                <h6 className="text-sm ghosted text-blue-500">/architecture.pdf</h6>
                            </a>
                        </div>
                        <div
                            className="block max-w-sm p-5 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-2 text-md font-bold tracking-tight text-gray-900 ">Method
                                selection <br/> and planning</h5>
                            <p className="text-xs font-normal text-gray-700 pb-5">Here is a link to our method selection
                                and planning PDF.</p>
                            <a className="cursor-pointer" href="/old_deliverables/methodselection.pdf" target="_blank"
                               rel="noopener noreferrer" download>
                                <h6 className="text-sm ghosted text-blue-500">/methodplanning.pdf</h6>
                            </a>
                        </div>
                    </div>
                    <div className="flex flex-row gap-3">
                        <div
                            className="block max-w-sm p-5 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-2 text-md font-bold tracking-tight text-gray-900 ">Risk
                                assessment <br/> and mitigation </h5>
                            <p className="text-xs font-normal text-gray-700 pb-5">Here is a link to our risk assessment
                                and mitigation PDF.</p>
                            <a className="cursor-pointer" href="/old_deliverables/riskassessment.pdf" target="_blank"
                               rel="noopener noreferrer" download>
                                <h6 className="text-sm ghosted text-blue-500">/riskassessment.pdf</h6>
                            </a>
                        </div>
                        <div
                            className="block max-w-sm p-5 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 ">
                            <h5 className="mb-2 text-md font-bold tracking-tight text-gray-900 ">Implementation</h5>
                            <p className="text-xs font-normal text-gray-700 pb-5">Here is a link to our implementation
                                PDF.</p>
                            <a className="cursor-pointer" href="/old_deliverables/implementation.pdf" target="_blank"
                               rel="noopener noreferrer" download>
                                <h6 className="text-sm ghosted text-blue-500">/implementation.pdf</h6>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </section>


    )
        ;
}

export default HeroSection;
