"use client";
import React, { useState } from "react"
import Link from "next/link"
import { AiOutlineMenu, AiOutlineClose } from 'react-icons/ai'
import { Icons } from "@/components/icons"


interface NavItem{
    label: string
    page: string
}

const nav_items: Array<NavItem> = [
    {
        label: "Home",
        page: "/",
    },
    {
      label: "Documents",
      page: "/",
  },
]


export function Navbar() {    
    const [menuOpen, setMenuOpen] = useState(false)
    const handleNav = () =>{
      setMenuOpen(!menuOpen);
    }
    
   

  return (
    <header className="top-0 z-50 w-full p-5 py-3 shadow bg-white">
      <div className ="flex items-center justify-between w-full px-4 2xl:px-16">
        <div className = "">
        <a href="/">
            <div className = "flex items-center gap-3">
                {/* <img src="/logo-grayed.png"  className="mr-2 w-[40px] h-[40px]" /> */}
                <h1 className="text-lg font-semibold">/eng1/cohort1group1</h1>
            </div>
          </a>
        </div>
        <div className="space-x-4 ml-auto mr-6 hidden sm:flex cursor-pointer text-sm">
            {nav_items.map((item, idx) =>  {
                return <a href={item.page} key={idx}>{item.label}</a>
            })}
            <div className="flex gap-5 pl-4">
            <Icons.gitHub className="h-5 w-5 cursor-pointer" />
            </div>
            
        </div>
        <div>
            {/* <a href="/contact">
              <button className="bg-blue-500 rounded p-2.5 shadow text-xs text-white hidden sm:flex ">
                Contact me
              </button>      
            </a> */}
  
        </div>
        

        <div onClick={handleNav} className="sm:hidden cursor-pointer pl-24">
          {!menuOpen && <AiOutlineMenu size={25} />}
          {menuOpen && <span style={{ display: "inline-block", height: "25px", width: "25px" }} />}
        </div>

      </div>
      
      <div className={
        menuOpen 
        ? "fixed left-0 top-0 w-[65%] sm:hidden h-screen bg-[#ecf0f3] p-10 ease-in duration-300"
      : "fixed left-[-100%] top-0 p-10 ease-in duartion-300"
      }
      >
        <div className="flex w-full items-center justify-end">
          <div onClick = {handleNav} className = "cursor-pointer">
            <AiOutlineClose  size={25}/>
          </div>
        </div>
        <div className = "flex-col py-4">
            <ul>
              <Link href="/">
                <li onClick = {() => setMenuOpen(false)}
                className = "py-4 cursor-pointer"
                >
                  Home
                </li>
              </Link>
              <Link href="/about">
                <li onClick = {() => setMenuOpen(false)}
                className = "py-4 cursor-pointer"
                >
                  Documents
                </li>
              </Link>
            </ul>
        </div>
        <div className="flex gap-5">
        <Icons.gitHub className="h-5 w-5" />

        </div>
      </div>
    </header>
    
  )
}
