// next.config.js
module.exports = {
  // Export the site to static HTML
  // https://nextjs.org/docs/advanced-features/static-html-export
  // Use generateStaticParams() to define the pages you want to export
  async generateStaticParams() {
    return {
      '/': { page: '/' }
      // Add additional pages here if needed
    };
  }
};
