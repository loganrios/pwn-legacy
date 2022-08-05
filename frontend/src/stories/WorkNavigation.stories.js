import React from 'react'
import WorkNavigation from './WorkNavigation';

export default {
  title: 'WorkNavigation',
  component: WorkNavigation,
  argTypes: {
    onNext: { action: "The Next is the Best" },
    onPrev: { action: "Don't dwell on the past" },
    onToggleTrack: { action: "Call the FBI" },
  }
};

const Template = (args) => <WorkNavigation {...args} />;

export const TrackingProg = Template.bind({});
TrackingProg.args = {
  isTracked: true,
  canToggleTrack: true,
};

export const NotTracking = Template.bind({});
NotTracking.args = {
  isTracked: false,
  canToggleTrack: true,
};

export const GuestView = Template.bind({});
GuestView.args = {
  isTracked: false,
  canToggleTrack: false,
};
