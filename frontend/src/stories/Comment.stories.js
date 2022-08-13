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

export const Primary = Template.bind({});
Primary.args = {
  username: "Loganicus Swole",
  timestamp: "8/12/22 5:50 PM",
  commentText:
    "This is a placeholder comment. It does not have any value. The implications of what it says are up to you to decide. Here are more words to make it longer.",
  defaultAvatar: "L",
  avatarImage: "",
};
