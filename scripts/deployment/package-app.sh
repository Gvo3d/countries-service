#!/usr/bin/env bash
cd ../../
mkdir -p ./webapp
cd ./scripts/deployment
cp -v ./web-install.sh ../../webapp
cp -v ./web-launch.sh ../../webapp
cd ../../web-ui
cp -r `ls -A | grep -v "node_modules"` ../webapp/
cd ../
tar -zcvf webapp.tar.gz ./webapp/
rm -rf ./webapp/