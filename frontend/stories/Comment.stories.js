import React from "react";
import Comment from "./Comment";

export default {
  title: "Comment",
  component: Comment,
  argTypes: {
    onEdit: { action: "Change is inevitable" },
    onReply: { action: "Leave me alone" },
  },
};

const Template = (args) => <Comment {...args} />;

export const Owner = Template.bind({});
Owner.args = {
  isOwner: true,
  isLocked: false,
  username: "Loganicus Swole",
  timestamp: "8/12/22 5:50 PM",
  text:
    "This is a placeholder comment. It does not have any value. The implications of what it says are up to you to decide. Here are more words to make it longer.",
  defaultAvatar: "L",
  image: "https://avatarfiles.alphacoders.com/594/59437.jpg",
};

export const OwnerLocked = Template.bind({});
OwnerLocked.args = {
  isOwner: true,
  isLocked: true,
  username: "Loganicus Swole",
  timestamp: "8/12/22 5:50 PM",
  text:
    "This is a placeholder comment. It does not have any value. The implications of what it says are up to you to decide. Here are more words to make it longer.",
  defaultAvatar: "L",
  image: "https://avatarfiles.alphacoders.com/594/59437.jpg",
};

export const NotOwner = Template.bind({});
NotOwner.args = {
  isOwner: false,
  isLocked: false,
  username: "Loganicus Swole",
  timestamp: "8/12/22 5:50 PM",
  text:
    "This is a placeholder comment. It does not have any value. The implications of what it says are up to you to decide. Here are more words to make it longer.",
  defaultAvatar: "L",
  image: "https://avatarfiles.alphacoders.com/594/59437.jpg",
};

export const NotOwnerLocked = Template.bind({});
NotOwnerLocked.args = {
  isOwner: false,
  isLocked: true,
  username: "Loganicus Swole",
  timestamp: "8/12/22 5:50 PM",
  text:
    "This is a placeholder comment. It does not have any value. The implications of what it says are up to you to decide. Here are more words to make it longer.",
  defaultAvatar: "L",
  image: "https://avatarfiles.alphacoders.com/594/59437.jpg",
};
