# Containerization

## Docker Installation
**Reference**

> https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-16-04

### Install docker using the following instructions:
##### Add the gpg key for installation
> curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

##### Add docker repository to ubuntu
> sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

##### Update apt-get
> sudo apt-get update

##### Check the cache policy of docker
> apt-cache policy docker-ce

##### Finally install docker
> sudo apt-get install -y docker-ce

##### Check the status of the docker installation
> sudo systemctl status docker

#####For reference of markdown syntax [MarkDown Guide](https://www.markdownguide.org/basic-syntax/ "MarkDown scripting cheat sheet.")