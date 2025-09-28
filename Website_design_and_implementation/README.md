# Setup tutorial <!-- omit in toc -->
- [Install git](#install-git)
- [Clone repository](#clone-repository)
- [Switch branch](#switch-branch)

# Install git
Head to this link and download the latest stable version:  
https://git-scm.com/downloads  

Run the file, you can skip most steps however, for your own sake 
I recommend changing your default editor to VSCode during setup
(see image attached below):

![setup default browser step](https://cdn.discordapp.com/attachments/564869622206300172/1301529488735211662/44156291-f701f178-a085-11e8-8870-de127ca622cd.png?ex=6724cf4b&is=67237dcb&hm=dea5bde6867867a475f8a12024e9b7b8a6946ec71a2e97e5bd766a7f411e159f&)

You are done once git finishes installing (crazy ik)

# Clone repository

If you already have git you can click **Code** and then copy the repo web URL (see image attached below):

![Copy repo web url](https://cdn.discordapp.com/attachments/564869622206300172/1301536214335225936/image.png?ex=6724d58e&is=6723840e&hm=38311e93a81a2cddb02f2365e7176d500462d588068611b46023dc9bdf63d385&)

Now, open file explorer in the folder where you wish to store the project and do:  
right click -> Open Git Bash here

![Open Git Bash here](https://cdn.discordapp.com/attachments/564869622206300172/1301537971169792081/image.png?ex=6724d731&is=672385b1&hm=96a99d7b167767fb962530ca8f3460d002bb0d74d880929565c4ae37db5a7a89&)

In the terminal paste this code (change $url to the URL you copied from github):
```sh
git clone $url
```

Authenticate yourself with browser to begin cloning.

If you have done everything correctly a folder with the name will be created, you can open it in the terminal by entering:
```sh
cd ./VIAPets
```

If everything has been done correctly your terminal should look something like this: 
![Terminal with project open](https://cdn.discordapp.com/attachments/564869622206300172/1301542224361689089/image.png?ex=6724db27&is=672389a7&hm=5b1be12fa33ae17e1cf0e40b1c3f806b919381d48a465be6ad70787b97d24dca&)

# Switch branch

Now all that's left to start working on the project is to change the branch you are working on. If you want to learn what branches are there is a myriad of resources you can find on the matter.  
Right now all you need to do is we do not work on the main branch.

To switch your working branch enter this command into your git bash terminal (assignment2 is the branch name for our upcoming assignment):
```sh
git checkout assignment2
```

Your terminal should look something like this:
![post switch branch](https://cdn.discordapp.com/attachments/564869622206300172/1301547320080470046/image.png?ex=6724dfe6&is=67238e66&hm=f43b25fda7ff0ae4d71af0f2d309c77b1caf9a0585afd194a3fdfaa60f5d419b&)