#!/bin/bash
sudo cp $(cd `dirname $0`; pwd)/systemd/studyzookeeper.service /etc/systemd/system/
sudo systemctl enable studyzookeeper
sudo systemctl start studyzookeeper