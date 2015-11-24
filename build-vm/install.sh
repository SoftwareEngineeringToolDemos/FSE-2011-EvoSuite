sudo apt-get update
sudo wget --no-check-certificate https://github.com/aglover/ubuntu-equip/raw/master/equip_java8.sh && bash equip_java8.sh
echo "Downloading eclipse, this might take a while ..."
sudo wget -nv "http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/mars/1/eclipse-rcp-mars-1-linux-gtk-x86_64.tar.gz&r=1" -O eclipse-rcp-mars-1-linux-gtk-x86_64.tar.gz
echo "Installing eclipse in /opt/eclipse and setting up paths ..."
cd /home/vagrant && sudo tar xzf /home/vagrant/eclipse-rcp-mars-1-linux-gtk-x86_64.tar.gz
sudo ln -s /home/vagrant/eclipse/eclipse /home/vagrant/Desktop/eclipse
sudo apt-get install -y git
git clone https://github.com/SoftwareEngineeringToolDemos/FSE-2011-EvoSuite.git /home/vagrant/Desktop/FSE-2011-EvoSuite
sudo chmod a+rwx /home/vagrant/Desktop/FSE-2011-EvoSuite
sudo mv /home/vagrant/Desktop/FSE-2011-EvoSuite/build-vm/vm-contents/* /home/vagrant/Desktop/
sudo rm -rf /home/vagrant/Desktop/FSE-2011-EvoSuite
sudo chown $USER -R /home/vagrant
sudo chown $USER -R /home/vagrant/Desktop
