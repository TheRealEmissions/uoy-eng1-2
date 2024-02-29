import HeroSection from "@/components/HeroSection"
import ContentSection from "@/components/ContentSection"
import DownloadSection from "@/components/DownloadSection"


export default function Home() {
  return (
   <main className="min-h-screen">
    <HeroSection />
    <ContentSection />
    <DownloadSection />
    </main>
  );
}
