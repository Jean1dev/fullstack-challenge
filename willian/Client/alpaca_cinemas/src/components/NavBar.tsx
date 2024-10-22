import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const Navbar = () => {
  const isLogged = false;
  const navigate = useNavigate();

  return (
    <div className="navbar bg-custom-dark-blue">
      <div className="navbar-start">
        <div className="dropdown">
          <div tabIndex={0} role="button" className="btn btn-ghost lg:hidden">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              className="h-5 w-5"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                d="M4 6h16M4 12h8m-8 6h16"
              />
            </svg>
          </div>
          <ul
            tabIndex={0}
            className="menu menu-sm dropdown-content bg-custom-dark-blue rounded-box z-[1] mt-3 w-52 p-2 shadow"
          >
            <li>
              <Link to="/">Home</Link>
            </li>
            {!isLogged ? (
              <>
                <li>
                  <Link to="">Login</Link>
                </li>
                <li>
                  <a className="btn bg-custom-gray border-0 text-white">
                    Seja Menbro
                  </a>
                </li>
              </>
            ) : (
              <></>
            )}
          </ul>
        </div>
        <Link className="btn btn-ghost text-xl" to="/">
          Alpaca Filmes
        </Link>
      </div>
      <div className="navbar-center hidden lg:flex">
        <ul className="menu menu-horizontal px-1">
          <li>
            <Link to="/">Home</Link>
          </li>
        </ul>
      </div>
      <div
        className={
          !isLogged
            ? "navbar-end hidden lg:flex gap-2"
            : "navbar-end lg:flex gap-2"
        }
      >
        {isLogged ? (
          <div className="dropdown dropdown-end">
            <div
              tabIndex={0}
              role="button"
              className="btn btn-ghost btn-circle avatar"
            >
              <div className="w-10 rounded-full">
                <img
                  alt="Tailwind CSS Navbar component"
                  src="https://img.daisyui.com/images/stock/photo-1534528741775-53994a69daeb.webp"
                />
              </div>
            </div>
            <ul
              tabIndex={0}
              className="menu menu-sm dropdown-content bg-custom-dark-blue rounded-box z-[1] mt-3 w-52 p-2 shadow"
            >
              <li>
                <a className="justify-between">Profile</a>
              </li>
              <li>
                <a>Logout</a>
              </li>
            </ul>
          </div>
        ) : (
          <>
            <Link to="/">Login</Link>
            <a className="btn btn-primary text-white bg-custom-blue border-0 hover:text-gray-100">
              Seja Mebro
            </a>
          </>
        )}
      </div>
    </div>
  );
};

export default Navbar;
