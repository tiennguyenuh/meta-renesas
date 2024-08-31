# remove virtual/libx11 in the bb file
PACKAGECONFIG[x11] = "-Dglx=yes, -Dglx=no -Dx11=false, virtual/libgl"
