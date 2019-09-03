# Install Ansible
Note: Ansible cannot be installed on Windows, however Ansible can configure Windows and non windows

## Step-1 (Setting Up Ansible Controle Node)
  - Ansible Control machine requirements
    ```
       Currently Ansible can be run from any machine with Python 2 (version 2.7) or Python 3 
       (versions 3.5 and higher) installed. 
       Windows isn’t supported for the control node.
    ```
  - Launch Ubuntu from AWS
  - SSH into Ubuntu
  - 
    ``` 
      sudo apt-get update 
      sudo apt-get install ansible -y 
      ansible --version
      
    ```

## Step-2 (Setting Up Ansible Managed Node)

Launch Linux machine for managed Node.

On the managed nodes, you need a way to communicate, which is normally ssh. By default this uses sftp. 
If that’s not available, you can switch to scp in ansible.cfg. 
You also need Python 2 (version 2.6 or later) or Python 3 (version 3.5 or later).


## Ansible Inventory File
It is the configuration file used by Ansible to locate its managed nodes to configure. In inventory file you will
find details like
  - Node Ip
  - User name to use
  - Password or Private Key 
  - Connection type (SSH or Winrm)
  - Many more..
Ansible inventory file comes along with installation and by default you can locate it at
``` /etc/ansible/hosts  ```

Open above file and add managed node details

```
  172.31.12.119 ansible_user=ec2-user ansible_ssh_private_key_file=~/ansible.pe
m ansible_connection=ssh
```
Note: Make sure your ansible.pem is present at correct location.
      Make sure youp pem file has correct permissions ```  chmod 600 ansible.pem ```

### Test connectivity between Controle Node and Managed Node

``` ansible your-ip -m ping ```

  - ``` -m  ``` is module and ```ping`` is module name

## Ansible ad-hoc commands
### Install Git on Managed Node
  ``` ansible your-host -m yum -a 'name=git state=present' --become ```
  1. yum is module
  2. git is package name
  3. state = present i.e. ansible will make sure git is present on managed if not install it
  4. --become tells ansible to run commands in sudo mode
### Check if Git is installed or not
```  ansible your-host -m command -a 'git --version' ```
