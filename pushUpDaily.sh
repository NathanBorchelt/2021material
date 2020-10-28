#!/bin/bash

DATE=$(date)

DATE+'%c'

PUSH="Push Up on"

echo "What did you do today:  "

read REASON

COMMENT="$PUSH at $DATE from $HOSTNAME. Reason:  $REASON"

cd

cd C:\\Users\\CS-PM\\Documents\\2021material

git add .



git commit -m "$COMMENT"

git push

