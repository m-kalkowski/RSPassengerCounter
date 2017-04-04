if [ $# -eq 0 ]; then
  echo "No arguments supplied"
  echo "This script is going to install the needed configuration file to build the poky image. Please insert your Yocto build path."
  echo "Supported format: /path/to/poky/folder"
  exit 1
fi

echo "Input: $1"

if [ -d "$1/poky/filec" ]; then
  echo "Directory filec found."
    
  cp auto.conf $1/poky/filec/conf
  cp bblayers.conf $1/poky/filec/conf
  rm $1/poky/filec/conf/local.conf
  cp local.conf $1/poky/filec/conf
  cp opencv_3.1.bb $1/poky/meta-openembedded/meta-oe/recipes-support/opencv/

elif [ -d "$1/poky/build" ]; then
  echo "Directory build found."

  cp auto.conf $1/poky/build/conf
  cp bblayers.conf $1/poky/build/conf
  rm $1/poky/build/conf/local.conf
  cp local.conf $1/poky/build/conf
  cp opencv_3.1.bb $1/poky/meta-openembedded/meta-oe/recipes-support/opencv/

else
  echo "No build directory found. Chech path"
fi
