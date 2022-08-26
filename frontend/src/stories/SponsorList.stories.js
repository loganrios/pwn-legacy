import React from "react";
import SponsorList from "./SponsorList";

export default {
  title: "SponsorList",
  component: SponsorList,
  argTypes: {
  },
};

const Template = (args) => <SponsorList {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  sponsors: [
    {
      // avatar: "",
      id: 1,
      username: "Loganicus Swole",
      donation: 10,
    },
    {
      // avatar: "",
      id: 2,
      username: "Yolo Swaggins",
      donation: 8,
    },
    {
      // avatar: "",
      id: 4,
      username: "TheAnswer42",
      donation: 5,
    },
    {
      // avatar: "",
      id: 3,
      username: "TheAnswer43",
      donation: 5,
    },
  ],
};
