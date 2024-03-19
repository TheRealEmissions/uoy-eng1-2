import React from 'react'
import { ArrowUpRight } from 'lucide-react';
import ContentSection from "@/components/ContentSection"



export default function Home() {
  return (
   <main className="min-h-screen ">

    <ContentSection />

    <section id="Diagram section" className="xl:p-24 md:p-24 lg:p-24 p-5 flex justify-center items-center ">
        <div className="container p-3">
            <h2 className="font-semibold text-5xl pt-5 pb-10">
                Our Diagrams
            </h2>
            <div className="flex flex-col md:flex-row md:gap-48 gap-10 pt-16">
                <div>
                    <p className="text-sm">Week 1</p>
                    <h3 className="text-2xl md:text-2xl font-semibold">
                        Gannt Chart 1
                    </h3>
                    <div className="max-w-xl pt-5 text-sm">
                        <h2 className="text-xl font-bold">Key tasks:</h2>
                        <h3 className="text-lg font-semibold">Method Selection and Planning (High Priority) </h3>
                        <p>Start: 2024-02-19, End: 2024-03-15</p>
                        <p>Includes Research, Planning, Gantt Chart Creation,  Documentation</p>
                        <p>Dependencies: None</p>
                        <h3 className="text-lg font-semibold">Website Creation (Medium Priority)</h3>
                        <p>Start: 2024-02-24, End: 2024-03-01</p>
                        <p>Includes Domain creation on Github, Website Design</p>
                        <p>Dependencies: None</p>
                        <h3 className="text-lg font-semibold">Risk Management (Medium Priority)</h3>
                        <p>Start: 2024-02-25, End: 2024-03-15</p>
                        <p>Includes Introduction, Writing Risks, Documentation</p>
                        <p>Dependencies: None</p>

                    </div>
                </div>
                <div className="max-w-xl min-w-xl md:min-w-xl">
                    <img src="Gantchart1.jpeg" className="rounded-xl w-full" alt="Gannt Chart" />
                </div>
            </div>

            <div className="flex flex-col md:flex-row gap-10 pt-16">
                <div>
                    <p className="text-sm">Week 2</p>
                    <h3 className="text-xl md:text-2xl font-semibold">
                        Gannt chart 2
                    </h3>
                    
                    <div className="max-w-xl pt-5 text-sm">
                        <h2 className="text-xl font-bold">Key tasks:</h2>
                        <h3 className="text-lg font-semibold">Requirement Collection (High Priority)</h3>
                        <p>Start: 2024-03-06, End: 2024-03-19</p>
                        <p>Includes Customer Interviews, Writing User/System Requirements, Documentation</p>
                        <p>Dependencies: None</p>
                        <h3 className="text-lg font-semibold">Implementation (Highest Priority)</h3>
                        <p>Start: 2024-02-28, End: 2024-04-11</p>
                        <p>Includes Menu Creation, Map/Navigation Design, Character Design/Movement, Collision Detection, Interior Design, Map Transitions, Integration</p>
                        <p>Dependencies: Requirement Collection</p>
                    </div>
                </div>
                <div className="max-w-xl min-w-xl md:min-w-xl">
                    <img src="Gantchart2.jpeg" className="rounded-xl w-full" alt="Gannt Chart" />
                </div>
            </div>

            <div className="flex flex-col md:flex-row gap-10 pt-16">
                <div>
                    <p className="text-sm">Week 3</p>
                    <h3 className="text-xl md:text-2xl font-semibold">
                        Gannt Chart 3
                    </h3>
                    <div className="max-w-xl pt-5 text-sm">
                        <h2 className="text-xl font-bold">Key tasks:</h2>
                        <h3 className="text-lg font-semibold">Architecture (High Priority)</h3>
                        <p>Start: 2024-03-10, End: 2024-03-21</p>
                        <p>Includes System Design, Database Design</p>
                        <p>Dependencies: None</p>
                        <h3 className="text-lg font-semibold">Links Creation (Low Priority)</h3>
                        <p>Start: 2024-03-16, End: 2024-03-16</p>
                        <br></br>
                        <p>Dependencies: Implementation, Website Creation, Architecture, Risk management, Method selection and planning</p>
                    </div>
                </div>
                <div className="max-w-xl min-w-xl md:min-w-xl">
                    <img src="Gantchart3.jpeg" className="rounded-xl w-full" alt="Gannt Chart" />
                </div>
            </div>
        </div>
    </section>
    </main>
  );
}
