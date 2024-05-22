// noinspection HtmlRequiredAltAttribute

import React from 'react';

const Figures = () => {
  return (
      <section id="Diagram section" className="xl:p-24 md:p-24 lg:p-24 p-5 flex justify-center items-center ">
        <div className="flex flex-col md:flex-row gap-10 md:gap-52 pt-16">
          <div>
            <p className="text-sm">Figure 1</p>
            <h3 className="text-xl md:text-2xl font-semibold">
              Architecture
            </h3>
            <div className="max-w-xl text-sm">
              <h2 className="text-xl">Updated Class Diagram</h2>

            </div>
          </div>
          <div className="max-w-xl min-w-xl md:min-w-xl">
            <img src="/updated_deliverables/New%20Class%20Diagram.jpg" className="rounded-xl w-full"/>
          </div>
        </div>
      </section>
  );
};

export default Figures;
