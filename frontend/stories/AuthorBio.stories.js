import React from "react";
import AuthorBio from "./AuthorBio";

export default {
  title: "AuthorBio",
  component: AuthorBio,
  argTypes: {
    onFollow: { action: "Follow me into the great unknown" },
    onSponsor: { action: "Shut up and take my money!" },
  },
};

const Template = (args) => <AuthorBio {...args} />;

export const Owner = Template.bind({});
Owner.args = {
  isOwner: true,
  username: "Loganicus Swole",
  statsText: "Followers: 42, Sponsors: 7, Following, 39",
  bioText:
    "My name is Loganicus Swole and I like to read books about weight lifting ship my favorite characters.",
  image: "https://avatarfiles.alphacoders.com/594/59437.jpg",
};

export const Visitor = Template.bind({});
Visitor.args = {
  isOwner: false,
  username: "Loganicus Swole",
  statsText: "Followers: 42, Sponsors: 7, Following, 39",
  bioText:
    "My name is Loganicus Swole and I like to read books about weight lifting ship my favorite characters.",
  image: "https://avatarfiles.alphacoders.com/594/59437.jpg",
};
