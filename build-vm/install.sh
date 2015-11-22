sudo apt-get update
sudo wget --no-check-certificate https://github.com/aglover/ubuntu-equip/raw/master/equip_java8.sh && bash equip_java8.sh
echo "Downloading eclipse, this might take a while ..."
wget -nv "http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/mars/1/eclipse-rcp-mars-1-linux-gtk-x86_64.tar.gz&r=1" -O eclipse-rcp-mars-1-linux-gtk-x86_64.tar.gz
echo "Installing eclipse in /opt/eclipse and setting up paths ..."
(cd /opt && sudo tar xzf /home/vagrant/eclipse-rcp-mars-1-linux-gtk-x86_64.tar.gz)
sudo ln -s /opt/eclipse/eclipse ~/Desktop/eclipse
git clone https://github.com/SoftwareEngineeringToolDemos/FSE-2011-EvoSuite.git /home/vagrant/Desktop/FSE-2011-EvoSuite
chmod +x /home/vagrant/Desktop/FSE-2011-EvoSuite
mv /home/vagrant/Desktop/FSE-2011-EvoSuite/build-vm/vm-contents/* /home/vagrant/Desktop/
rm -rf /home/vagrant/Desktop/FSE-2011-EvoSuite
