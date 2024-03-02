/** @type {import('next').NextConfig} */
const nextConfig = {
  favicon: "/icon.ico",
  output: "export",
  images: {
      unoptimized: true
  },
  trailingSlash: true,
}

module.exports = nextConfig