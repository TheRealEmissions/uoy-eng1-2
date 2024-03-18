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
            
            <div className="flex flex-col md:flex-row gap-10 pt-20">
                <div className="max-w-xl min-w-xl md:min-w-xl">
                    <img src="placeholder1.png" className="rounded-xl w-full" alt="Gannt Chart" />
                </div>
                <div>
                    <h3 className="text-xl md:text-2xl font-semibold">
                        Architecture figure 1.1
                    </h3>
                    <p className="max-w-xl pt-5">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi commodo tellus sit amet <span className="font-bold">magna tempor </span>vestibulum. Sed lobortis laoreet sem, ut laoreet erat aliquam non.
                    </p>
                </div>
            </div>

            <div className="flex flex-col md:flex-row gap-10 pt-10">
                <div className="max-w-xl min-w-xl md:min-w-xl">
                    <img src="placeholder2.png" className="rounded-xl w-full" alt="Gannt Chart" />
                </div>
                <div>
                    <h3 className="text-xl md:text-2xl font-semibold">
                        Architecture figure 2.2
                    </h3>
                    <p className="max-w-xl pt-5">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi commodo tellus sit amet <span className="font-bold">magna tempor </span>vestibulum. Sed lobortis laoreet sem, ut laoreet erat aliquam non.
                    </p>
                </div>
            </div>

            <div className="flex flex-col md:flex-row gap-10 pt-10">
                <div className="max-w-xl min-w-xl md:min-w-xl">
                    <img src="placeholder3.png" className="rounded-xl w-full" alt="Gannt Chart" />
                </div>
                <div>
                    <h3 className="text-xl md:text-2xl font-semibold">
                        Gannt Chart
                    </h3>
                    <p className="max-w-xl pt-5">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi commodo tellus sit amet <span className="font-bold">magna tempor </span>vestibulum. Sed lobortis laoreet sem, ut laoreet erat aliquam non.
                    </p>
                </div>
            </div>
        </div>
    </section>
    </main>
  );
}
