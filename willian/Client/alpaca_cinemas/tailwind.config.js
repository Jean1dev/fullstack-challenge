/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      colors: {
        "custom-blue": "#93B3F1",
        "custom-gray": "#5C8C8C",
        "custom-dark-blue": "#2D3A4E",
        "custom-red": "#AE5959",
        "custom-light-blue": "#3285D2"
      }
    }
  },
  plugins: [require("daisyui")]
};
