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
            
            <div className="flex flex-col md:flex-row gap-10 pt-16">

                <div>
                    <h3 className="text-xl md:text-2xl font-semibold">
                        Gannt chart 1
                    </h3>
                    <p className="max-w-xl pt-5 text-sm">
                        Week 1
                        <br></br>
                        <br></br>
                        Key tasks:

                        <h3 className="text-lg">Method Selection and Planning (High Priority) </h3>
                        Start: 2024-02-19, End: 2024-03-15
                        <br></br>
                        Includes Research, Planning, Gantt Chart Creation
                        <br></br>
                        Dependencies: None
                        <br></br>
                        <br></br>
                        <h3 className="text-lg">Website Creation (Medium Priority)</h3>
                        Start: 2024-02-24, End: 2024-03-01
                        <br></br>
                        Includes Domain creation on Github, Website Design
                        <br></br>
                        Dependencies: None
                        <br></br>
                        Risk Management (Medium Priority)
                        <br></br>
                        Start: 2024-02-25, End: 2024-03-15
                        <br></br>
                        Includes Introduction, Writing Risks, Documentation
                        <br></br>
                        Dependencies: Product brief

                    </p>
                </div>
                <div className="max-w-xl min-w-xl md:min-w-xl">
                    <img src="Gantchart1.jpeg" className="rounded-xl w-full" alt="Gannt Chart" />
                </div>
            </div>

            <div className="flex flex-col md:flex-row gap-10 pt-16">
                <div>
                    <h3 className="text-xl md:text-2xl font-semibold">
                        Gannt chart 2
                    </h3>
                    <p className="max-w-xl pt-5 text-sm">
                        Week 2
                        <br></br>
                        <br></br>
                        Key tasks:
                        <h3 className="text-lg">Requirement Collection (High Priority)</h3>
                        Start: 2024-03-06, End: 2024-03-19
                        <br></br>
                        Includes Customer Interviews, Writing User/System Requirements, Documentation
                        <br></br>
                        Dependencies: None
                        <br></br>
                        <br></br>
                        <h3 className="text-lg">Implementation (Highest Priority)</h3>
                        Start: 2024-02-28, End: 2024-04-11
                        <br></br>
                        Includes Menu Creation, Map/Navigation Design, Character Design/Movement, Collision Detection, Interior Design, Map Transitions, Integration
                        <br></br>
                        Dependencies: Requirement Collection

                    </p>
                </div>
                <div className="max-w-xl min-w-xl md:min-w-xl">
                    <img src="Gantchart2.jpeg" className="rounded-xl w-full" alt="Gannt Chart" />
                </div>
            </div>

            <div className="flex flex-col md:flex-row gap-10 pt-16">
                <div>
                    <h3 className="text-xl md:text-2xl font-semibold">
                        Gannt Chart 3
                    </h3>
                    <p className="max-w-xl pt-5 text-sm">
                        Week 3
                        <br></br>
                        <br></br>
                        Key Tasks:
                        <h3 className="text-lg">Architecture (High Priority)</h3>
                        Start: 2024-03-10, End: 2024-03-21
                        <br></br>
                        <h3 className="text-lg">Includes System Design, Database Design</h3>
                        Dependencies: None
                        <br></br>
                        Links Creation (Low Priority)
                        <br></br>
                        Start: 2024-03-16, End: 2024-03-16
                        <br></br>
                        Dependencies: Implementation, Website Creation, Architecture, Risk management, Method selection and planning
                    </p>
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
