#!/bin/bash
sudo cp $(cd `dirname $0`; pwd)/systemd/studykafka.service /etc/systemd/system/
sudo systemctl enable studykafka
sudo systemctl start studykafka