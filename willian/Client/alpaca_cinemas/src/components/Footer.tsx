import React from "react";

const Footer = () => {
  return (
    <footer className="footer footer-center bg-custom-dark-blue p-4">
      <aside>
        <p>
          Copyright © {new Date().getFullYear()} - Todos os direitos reservados
        </p>
      </aside>
    </footer>
  );
};

export default Footer;
