import HeroSection from "@/components/HeroSection"
import ContentSection from "@/components/ContentSection"
import DownloadSection from "@/components/DownloadSection"
import UpdatedContentSection from "@/components/UpdatedContentSection";


export default function Home() {
  return (
   <main className="min-h-screen">
    <HeroSection />
       <UpdatedContentSection />
       <br/>
       <ContentSection />
       <DownloadSection />
    </main>
  );
}
