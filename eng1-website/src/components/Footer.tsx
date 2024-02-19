"use client";
import { Icons } from "@/components/icons";
import { useState } from "react";

const Footer: React.FC = () => {
  const [activeLink, setActiveLink] = useState("");

  return (
    <footer className="bg-[#f4f4f4]">
      <div className="container mx-auto py-4 pt-10 pb-10 p-16 mw-300">
        <div className="flex flex-col md:flex-row md:gap-4 justify-center items-center">
          <div className="grid grid-cols-2 sm:grid-cols-2 xs:grid-cols-2 gap-30 md:gap-10">
            <div className="">
              <h3 className="scroll-m-20 text-m font-semibold tracking-tight mb-2">
                game
              </h3>
              <ul>
                <li>
                  <a
                    className={`text-sm hover:text-blue-400 cursor-pointer ${
                      activeLink === "terms-of-service" ? "text-blue-400" : ""
                    }`}
                    href="/terms"
                    onClick={() => setActiveLink("terms-of-service")}
                  >
                    game download
                  </a>
                </li>
                <li>
                  <a
                    className={`text-sm hover:text-blue-400 cursor-pointer ${
                      activeLink === "cookie-settings" ? "text-blue-400" : ""
                    }`}
                    href="/cookies"
                    onClick={() => setActiveLink("cookie-settings")}
                  >
                    credits
                  </a>
                </li>
              </ul>
            </div>
            <div className="">
              <h3 className="scroll-m-20 font-bold tracking-tight mb-2">
                documents
              </h3>
              <ul>
                <li className="mb-1">
                  <a
                    className={`text-sm hover:text-blue-400 cursor-pointer ${
                      activeLink === "about" ? "text-blue-600" : ""
                    }`}
                    href="/"
                    onClick={() => setActiveLink("about")}
                  >
                    Requirements
                  </a>
                </li>
                {/* <li>
              <a
                    className={`text-sm hover:text-blue-400 cursor-pointer ${
                      activeLink === "how-it-works" ? "text-blue-400" : ""
                    }`}
                    href="/basics"
                    onClick={() => setActiveLink("how-it-works")}
                  >
                    how it works
                  </a>
              </li> */}
                <li className="mb-1">
                  <a
                    className={`text-sm hover:text-blue-400 cursor-pointer ${
                      activeLink === "contact" ? "text-blue-400" : ""
                    }`}
                    href="/"
                    onClick={() => setActiveLink("contact")}
                  >
                    Architecture
                  </a>
                </li>
                <li className="mb-2">
                  <a
                    className={`text-sm hover:text-blue-400 cursor-pointer ${
                      activeLink === "contact" ? "text-blue-400" : ""
                    }`}
                    href="/"
                    onClick={() => setActiveLink("contact")}
                  >
                    Method selection and planning
                  </a>
                </li>
                <li className="mb-2">
                  <a
                    className={`text-sm hover:text-blue-400 cursor-pointer ${
                      activeLink === "contact" ? "text-blue-400" : ""
                    }`}
                    href="/"
                    onClick={() => setActiveLink("contact")}
                  >
                    Risk assessment and mitigation
                  </a>
                </li>
                <li>
                  <a
                    className={`text-sm hover:text-blue-400 cursor-pointer ${
                      activeLink === "contact" ? "text-blue-400" : ""
                    }`}
                    href="/"
                    onClick={() => setActiveLink("contact")}
                  >
                    Implementation
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>

        <div className="container  mt-8">
          <div className="">
            <h3 className="scroll-m-20 text-xl font-bold tracking-tight mb-2">
              /group1/cohort1
            </h3>
          </div>
          <div className="hidden sm:flex">
            <p className="text-xs text-muted-foreground">
              /group1/cohort1 © {new Date().getFullYear()} All rights reserved.{" "}
            </p>
          </div>
          <div className="flex space-x-4"></div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
