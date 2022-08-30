import React from "react";
import AuthorBio from "./AuthorBio";

export default {
  title: "AuthorBio",
  component: AuthorBio,
  argTypes: {
    onFollow: { action: "Follow me into the great unknown" },
    onSponsorSubmit: { action: "Shut up and take my money!" },
    onEditAvatar: { action: "Change is inevitable." },
  },
};

const Template = (args) => <AuthorBio {...args} />;

export const Owner = Template.bind({});
Owner.args = {
  isOwner: true,
  username: "Loganicus Swole",
  wordcountText: "Wordcount: 69,420",
  publicReviewsText: "Public Reviews: 69",
  publicRatingsText: "Public Ratings: 420",
  bioText:
    "My name is Loganicus Swole and I like to read books about weight lifting ship my favorite characters.",
  image: "https://avatarfiles.alphacoders.com/594/59437.jpg",
};

export const Visitor = Template.bind({});
Visitor.args = {
  isOwner: false,
  username: "Loganicus Swole",
  wordcountText: "Wordcount: 69,420",
  publicReviewsText: "Public Reviews: 69",
  publicRatingsText: "Public Ratings: 420",
  bioText:
    "My name is Loganicus Swole and I like to read books about weight lifting ship my favorite characters.",
  image: "https://avatarfiles.alphacoders.com/594/59437.jpg",
  sponsorText:
    "Please enter the amount you are paying towards your sponsorship of this author.",
};
