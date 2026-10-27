public class Tasks1To5 {
    public static void main(String[] args) {
        Task1.main(args);
        System.out.println();
        Task2.main(args);
        System.out.println();
        Task3.main(args);
        System.out.println();
        Task4.main(args);
        System.out.println();
        Task5.main(args);
    }

    static class Task1 {
        public static void main(String[] args) {
            ProxyImage guestImage = new ProxyImage("salary_sheet.png", "GUEST");
            ProxyImage adminImage = new ProxyImage("salary_sheet.png", "ADMIN");
            guestImage.show();
            adminImage.show();
        }

        interface Image {
            void show();
        }

        static class RealImage implements Image {
            private String fileName;

            public RealImage(String fileName) {
                this.fileName = fileName;
                System.out.println("Loading " + fileName + "...");
            }

            @Override
            public void show() {
                System.out.println("Showing " + fileName);
            }
        }

        static class ProxyImage implements Image {
            private String fileName;
            private String userRole;
            private RealImage realImage;

            public ProxyImage(String fileName, String userRole) {
                this.fileName = fileName;
                this.userRole = userRole;
            }

            @Override
            public void show() {
                if (!"ADMIN".equals(userRole)) {
                    System.out.println("You can't open " + fileName);
                    return;
                }

                if (realImage == null) {
                    realImage = new RealImage(fileName);
                }
                realImage.show();
            }
        }
    }

    static class Task2 {
        public static void main(String[] args) {
            ProxyVideo video = new ProxyVideo("Cat_Memes.mp4");
            video.play();
            System.out.println();
            video.play();
            System.out.println();
            video.play();
        }

        interface Video {
            void play();
        }

        static class RealVideo implements Video {
            private String fileName;

            public RealVideo(String fileName) {
                this.fileName = fileName;
                System.out.println("Downloading " + fileName + "...");
            }

            @Override
            public void play() {
                System.out.println("Playing " + fileName);
            }
        }

        static class ProxyVideo implements Video {
            private String fileName;
            private RealVideo realVideo;
            private int viewCount = 0;

            public ProxyVideo(String fileName) {
                this.fileName = fileName;
            }

            @Override
            public void play() {
                viewCount++;
                System.out.println("Views: " + viewCount);

                if (realVideo == null) {
                    realVideo = new RealVideo(fileName);
                }
                realVideo.play();
            }
        }
    }

    static class Task3 {
        public static void main(String[] args) {
            SchoolProxyInternet internet = new SchoolProxyInternet();
            internet.connectTo("wikipedia.org");
            internet.connectTo("gaming.com");
        }

        interface Internet {
            void connectTo(String site);
        }

        static class RealInternet implements Internet {
            @Override
            public void connectTo(String site) {
                System.out.println("Opened " + site);
            }
        }

        static class SchoolProxyInternet implements Internet {
            private RealInternet realInternet;

            @Override
            public void connectTo(String site) {
                if ("gaming.com".equals(site) || "socialmedia.com".equals(site)) {
                    System.out.println("This site is blocked: " + site);
                    return;
                }

                if (realInternet == null){
                    realInternet =new RealInternet();
                }
                    realInternet.connectTo(site);
            }
        }
    }

    static class Task4 {
        public static void main(String[] args) {
            Device light = new SmartLight();
            AdvancedRemote remote = new AdvancedRemote(light);
            remote.powerOn();
            remote.mute();
        }

        interface Device {
            void turnOn();
            void turnOff();
            void setVolume(int level);
        }

        static class BasicRemote {
            protected Device device;

            public BasicRemote(Device device) {
                this.device = device;
            }

            public void powerOn() {
                device.turnOn();
            }

            public void powerOff() {
                device.turnOff();
            }

            public void setVolume(int level) {
                device.setVolume(level);
            }
        }

        static class AdvancedRemote extends BasicRemote {
            public AdvancedRemote(Device device) {
                super(device);
            }

            public void mute() {
                System.out.println("Muted.");
                device.setVolume(0);
            }
        }

        static class SmartLight implements Device {
            @Override
            public void turnOn() {
                System.out.println("Light is on.");
            }

            @Override
            public void turnOff() {
                System.out.println("Light is off.");
            }

            @Override
            public void setVolume(int level) {
                System.out.println("Light brightness: " + level + "%");
            }
        }
    }

    static class Task5 {
        public static void main(String[] args) {
            Radio radio = new Radio();
            PartyRemote remote = new PartyRemote(radio);
            remote.partyMode();
        }

        interface Device {
            void turnOn();
            void turnOff();
            void setVolume(int level);
        }

        static class BasicRemote {
            protected Device device;

            public BasicRemote(Device device) {
                this.device = device;
            }

            public void powerOn() {
                device.turnOn();
            }

            public void powerOff() {
                device.turnOff();
            }

            public void setVolume(int level) {
                device.setVolume(level);
            }
        }

        static class Radio implements Device {
            @Override
            public void turnOn() {
                System.out.println("Radio is on.");
            }

            @Override
            public void turnOff() {
                System.out.println("Radio is off.");
            }

            @Override
            public void setVolume(int level) {
                System.out.println("Radio volume: " + level);
            }
        }

        static class PartyRemote extends BasicRemote {
            public PartyRemote(Device device) {
                super(device);
            }

            public void partyMode() {
                System.out.println("Party mode is on!");
                device.turnOn();
                device.setVolume(100);
            }
        }
    }
}
