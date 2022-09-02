import React from "react";
import AuthorBio from "./AuthorBio";

export default {
  title: "AuthorBio",
  component: AuthorBio,
  argTypes: {
    onFollow: { action: "Follow me into the great unknown" },
    onSubmit: { action: "Shut up and take my money!" },
    onEditAvatar: { action: "Change is inevitable." },
  },
};

const Template = (args) => <AuthorBio {...args} />;

export const Owner = Template.bind({});
Owner.args = {
  isOwner: true,
  username: "Loganicus Swole",
  wordCount: "69,420",
  reviewsCount: "69",
  ratingsCount: "420",
  bio:
    "My name is Loganicus Swole and I like to read books about weight lifting ship my favorite characters.",
  image: "https://avatarfiles.alphacoders.com/594/59437.jpg",
};

export const Visitor = Template.bind({});
Visitor.args = {
  isOwner: false,
  username: "Loganicus Swole",
  wordCount: "69,420",
  reviewsCount: "69",
  ratingsCount: "420",
  bio:
    "My name is Loganicus Swole and I like to read books about weight lifting ship my favorite characters.",
  image: "https://avatarfiles.alphacoders.com/594/59437.jpg",
  desc:
    "Please enter the amount you are paying towards your sponsorship of this author.",
};
