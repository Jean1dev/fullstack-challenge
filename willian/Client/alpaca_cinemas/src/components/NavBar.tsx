import React, { useState } from "react";

const Navbar = () => {
  const isLogged = false;

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
              <a>Home</a>
            </li>
            {!isLogged ? (
              <>
                <li>
                  <a>Login</a>
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
        <a className="btn btn-ghost text-xl">Alpaca Filmes</a>
      </div>
      <div className="navbar-center hidden lg:flex">
        <ul className="menu menu-horizontal px-1">
          <li>
            <a>Home</a>
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
            <a>Login</a>
            <a className="btn text-white bg-custom-gray border-0">Seja Mebro</a>
          </>
        )}
      </div>
    </div>
  );
};

export default Navbar;
