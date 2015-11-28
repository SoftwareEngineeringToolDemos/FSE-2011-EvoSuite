echo "Enabling auto login"
sudo mkdir /etc/lightdm/lightdm.conf.d
sudo bash -c 'printf "[SeatDefaults]\nautologin-user=Vagrant" > /etc/lightdm/lightdm.conf.d/50-myconfig.conf'
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
sudo mv /home/vagrant/Desktop/FSE-2011-EvoSuite/evosuite-1.0.1.jar /home/vagrant/Desktop/EvoSuite
sudo mkdir /home/vagrant/.config/autostart
sudo mv /home/vagrant/Desktop/run_script.desktop /home/vagrant/.config/autostart
sudo rm -rf /home/vagrant/Desktop/FSE-2011-EvoSuite
#sudo rm /home/vagrant/Desktop/run_script.desktop
sudo chown vagrant -R /home/vagrant
sudo chown vagrant -R /home/vagrant/Desktop
sudo chmod +x /home/vagrant/Desktop/EvoSuite *
sudo reboot
